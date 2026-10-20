#!/usr/bin/perl
use strict;
use warnings;

my @name   = qw(Perl programming language);
my %author = (first => 'Larry', last => 'Wall');
my @uses   = ('text processing',
              'system administration');

my $text = "@name was developed by "
         . "$author{first} $author{last}, "
         . "initially as a unix scripting "
         . "language, known for "
         . join(' and ', @uses) . '.';

$text =~ s/\bunix\b/Unix/;   # regex
print "$text\n";
