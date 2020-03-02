#!/bin/python

import math
import os
import random
import re
import sys

#Nothing special, except that it's in python, I guess.

# Complete the gridSearch function below.
def gridSearch(G, P):
    for r in range(0, len(G)):
        for c in range(0, len(G[r])):
            if (len(G[r][c:]) < len(P[0])):
                break
            found = True
            for r2 in range(r, r+len(P)):
                if not re.match(P[r2-r], G[r2][c:]):
                    found = False
                    break
            if found:
                return "YES"
    return "NO"

if __name__ == '__main__':
    #fptr = open(os.environ['OUTPUT_PATH'], 'w')

    t = int(raw_input())

    for t_itr in xrange(t):
        RC = raw_input().split()

        R = int(RC[0])

        C = int(RC[1])

        G = []

        for _ in xrange(R):
            G_item = raw_input()
            G.append(G_item)

        rc = raw_input().split()

        r = int(rc[0])

        c = int(rc[1])

        P = []

        for _ in xrange(r):
            P_item = raw_input()
            P.append(P_item)

        result = gridSearch(G, P)

        print(result)

    #fptr.close()

