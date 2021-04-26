#  Naumen Test Task for intership
**Author: Mark Averchenko**

**telegram**: https://t.me/marcenavuc

# Idea of solution
Problem: build a route between two cell on map of chars.
But size of map maximum is 10000 x 10000, and this is a bottleneck
So standart breadth first algorithm now working efficiently,
and I used special metric (euclidean distance between start and end cells)
and priority queue for select nearest cell to end of maze

# Project architecture
**java/**  -  project folder 

* **BreadthFirstFinder** - class implements RouteFinder
* **Cell** - class contains data about position
* **EuclideanComparator** - class realizes calculating distance
between two cells
* **RouteFinder** - interface Finder

**tests/**  - tests
* **RouteFinderParametrizedTest** - simple parametrized test for algorithm
* **LoadTest** - load test for algorithm with maze 10000 x 10000