distributed_algorithms
======================

Distributed Algorithms coursework

This project implements some failure detector models for asynchronous distributed systems:

- Perfect Failure Detector:
    - A failure detector which suspects failed all failed processes processes
    (strong completeness) and never suspects a correct processes (strong
    accuracy)

- Eventually Perfect Detector:
    - A strongly complete failure detector which eventually does not suspect any
    correct processes (eventually strongly accurate)

- Strong Failure Detector
    - A strongly complete failure detector for which there exists at lest one
    correct process which is never suspected (weakly accurate)

- Eventually Strong Failure Detector
    - A strongly complete failure detector which will eventually have at least one
     correct process which it will not suspect

 These detectors have associated Process classes.
