package org.kd.hellogdx.polygons;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class CubeBall {
    private Mesh mesh;

    public CubeBall() {
        createCubeSphere(3); // 3x3 grid per face = ~18 polygons
    }

    private void createCubeSphere(int segments) {
        List<Vector3> vertices = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        // Create cube faces with subdivision
        for (int face = 0; face < 6; face++) {
            createFace(vertices, indices, face, segments);
        }

        float[] vertexData = new float[vertices.size() * 3];
        for (int i = 0; i < vertices.size(); i++) {
            Vector3 v = vertices.get(i);
            vertexData[i * 3] = v.x;
            vertexData[i * 3 + 1] = v.y;
            vertexData[i * 3 + 2] = v.z;
        }

        mesh = new Mesh(true, vertices.size(), indices.size(),
                new VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"));

        mesh.setVertices(vertexData);
        short[] array = new short[indices.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = indices.get(i).shortValue();
        }
        mesh.setIndices(array);
    }

    private void createFace(List<Vector3> vertices, List<Integer> indices, int face, int segments) {
        // Create vertices for this face
        int startIndex = vertices.size();

        // Generate vertices for this face
        for (int i = 0; i <= segments; i++) {
            for (int j = 0; j <= segments; j++) {
                Vector3 vertex = getFaceVertex(face, i, j, segments);
                vertices.add(vertex);
            }
        }

        // Create triangles for this face
        for (int i = 0; i < segments; i++) {
            for (int j = 0; j < segments; j++) {
                int a = startIndex + i * (segments + 1) + j;
                int b = startIndex + (i + 1) * (segments + 1) + j;
                int c = startIndex + i * (segments + 1) + (j + 1);
                int d = startIndex + (i + 1) * (segments + 1) + (j + 1);

                // First triangle
                indices.add(a);
                indices.add(b);
                indices.add(c);

                // Second triangle
                indices.add(b);
                indices.add(d);
                indices.add(c);
            }
        }
    }

    private Vector3 getFaceVertex(int face, int x, int y, int segments) {
        float u = (float) x / segments;
        float v = (float) y / segments;

        // Map to cube face coordinates
        Vector3 vertex = new Vector3();

        switch (face) {
            case 0: // Positive X
                vertex.set(1, -1 + 2 * u, -1 + 2 * v);
                break;
            case 1: // Negative X
                vertex.set(-1, -1 + 2 * u, -1 + 2 * v);
                break;
            case 2: // Positive Y
                vertex.set(-1 + 2 * u, 1, -1 + 2 * v);
                break;
            case 3: // Negative Y
                vertex.set(-1 + 2 * u, -1, -1 + 2 * v);
                break;
            case 4: // Positive Z
                vertex.set(-1 + 2 * u, -1 + 2 * v, 1);
                break;
            case 5: // Negative Z
                vertex.set(-1 + 2 * u, -1 + 2 * v, -1);
                break;
        }

        // Normalize to make it a sphere
        return vertex.nor();
    }

    public void render(ShaderProgram shader, Matrix4 transform) {
        if (mesh != null) {
            shader.setUniformMatrix("u_mvpMatrix", transform);
            mesh.render(shader, GL20.GL_TRIANGLES);
        }
    }
}
