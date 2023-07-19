# Binary Search Tree Using Abstractions

> This repository contains Java and Python code for implementing a Binary Search Tree (BST) using abstractions. The BST is a data structure that provides efficient insertion, deletion, and searching operations. This implementation utilizes concurrency and procedural abstraction: iteration.

## Table of Contents

- [Description](#description)
- [Prerequisites](#prerequisites)
- [Java Implementation](#java-implementation)
  - [Design Abstraction of Iteration](#design-abstraction-of-iteration)
  - [Merging Binary Search Trees](#merging-binary-search-trees)
- [Python Implementation](#python-implementation)
  - [Iterations and Coroutines in Python](#iterations-and-coroutines-in-python)
  - [Merging Binary Search Trees in Python](#merging-binary-search-trees-in-python)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Description

This repository contains Java and Python code for implementing a Binary Search Tree (BST) using abstractions. The BST is a fundamental data structure that maintains a sorted set of elements, allowing efficient insertion, deletion, and searching operations. The implementation incorporates concurrency and procedural abstraction through iteration, highlighting different approaches in Java and Python.

## Prerequisites

To compile and run the Java code in this repository, make sure you have the following installed:

- Java Development Kit (JDK) 1.8 (or a version compliant with Java 8)
- IntelliJ IDEA (recommended, but other IDEs can be used)

To run the Python code, ensure you have Python 3.6 or above installed, and use a suitable IDE such as PyCharm.

## Java Implementation

### Design Abstraction of Iteration

In Java, you will implement the Binary Search Tree as an iterable object by implementing the `Iterable` interface. This allows for easy iteration over the elements in increasing order. The BST supports insertion, deletion, and searching operations.

### Merging Binary Search Trees

The Java implementation includes a multi-threaded method called `merge`, which merges two Binary Search Trees concurrently to build a sorted list of their elements. The merge operation has linear time complexity and provides a single-pass over the trees.

## Python Implementation

### Iterations and Coroutines in Python

In the Python implementation, the Binary Search Tree is made iterable by implementing the `__iter__()` method, which acts as a generator. This approach allows for easy iteration while pausing and resuming the flow as needed.

### Merging Binary Search Trees in Python

The Python implementation includes a method called `merge` in the `Merger` class, which takes two Binary Search Trees as arguments and returns a single sorted list. The merge operation interleaves the iterations of the trees to produce the final sorted output.

## Contributing

Contributions to this repository are welcome! If you find any issues or want to improve the code, feel free to create a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

#### Contact
If you have any questions or feedback, you can contact the project maintainer at:

Email: pmtaday@gmail.com
GitHub: @ptaday
