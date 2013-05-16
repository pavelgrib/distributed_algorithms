""" 
Usage:
	da.py byz NUM
"""

import numpy as np
from docopt import docopt
import math


def byz_cons_num_msg(N):
	f = (N - 1)/3
	n_msg = 1
	for i in range(1, f+2):
		n_msg += (n_msg) * (N - i)
	return n_msg

if __name__ == '__main__':
	arguments = docopt( __doc__ )

	print byz_cons_num_msg( int(arguments['NUM']) )