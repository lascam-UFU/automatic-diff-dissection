# Patch Clustering [![Build Status](https://travis-ci.org/tdurieux/patch-clustering.svg?branch=master)](https://travis-ci.org/tdurieux/patch-clustering) [![Coverage Status](https://coveralls.io/repos/github/tdurieux/patch-clustering/badge.svg?branch=master)](https://coveralls.io/github/tdurieux/patch-clustering?branch=master)

This project extracts features from patches.

## Usage

### Setup Patch Clustering

1. Clone this repository:

```bash
$ git clone https://github.com/tdurieux/patch-clustering.git
```

2. Run the script `./init.sh`:

```bash
$ cd patch-clustering
$ chmod +x ./scripts/*.sh
$ ./scripts/init.sh
```

### Running Patch Clustering

To run Patch Clustering, you need to compile it, to locate the .jar file, and to call it:

```bash
$ cd patch-clustering
$ mvn package -DskipTests
$ ls target/*jar
target/patchclustering-0.1-SNAPSHOT.jar
target/patchclustering-0.1-SNAPSHOT-jar-with-dependencies.jar
target/patchclustering-0.1-SNAPSHOT-sources.jar
$ java -jar target/patchclustering-0.1-SNAPSHOT-jar-with-dependencies.jar <arguments>
```

Note that Patch Clustering receives a set of arguments to run. The argument options are:

```bash
  (-m|--launcherMode) <REPAIR_PATTERNS;REPAIR_ACTIONS;METRICS;ALL>
        Provide the launcher mode, which is the type of the features that will
        be extracted.

  (-b|--bugId) <bugId>
        Provide the bug id (this is used only for information presentation).

  --buggySourceDirectory <buggySourceDirectory>
        Provide the path to the buggy source code directory of the bug.

  --fixedSourceDirectory <fixedSourceDirectory>
        Provide the path to the fixed source code directory of the bug.

  --diff <diffPath>
        Provide the path to the diff file.

  [(-o|--output) <outputDirectory>]
        Provide an existing path to output the extracted features as a JSON file
        (optional).
```

The results will be printed in the terminal, and if a path was provided to the argument `outputDirectory`, the results will be saved in a JSON file.  

TODO: to show an usage case with one bug.

### Example on a dataset: extract features from the Defects4J

1. Clone and init Defects4J anywhere: follow instructions [here](https://github.com/rjust/defects4j)

2. Checkout each bug and patch by running the following script (WARNING: 12,3 GB in disk will be used):

```bash
$ ./patch-clustering/scripts/defects4j/checkout-defects4j/checkout_all.sh
```

3. Change the configuration in `/patch-clustering/scripts/defects4j/config.cfg` file:

- `defects4j` must be set with the path of the Defects4J repository cloned in Step 1.
- `checkout` must be set with the path of the directory containing the buggy versions checked out in Step 2.
- `fix_checkout` must be set with the path of the directory containing the fixed versions checked out in Step 2.

4. Enter in the directory where this project was cloned and compile this project:

```bash
$ mvn package -DskipTests
```

5. Run the feature extractor:

```bash
$ python scripts/defects4j/python/Main.py <REPAIR_PATTERNS;REPAIR_ACTIONS;METRICS;ALL>
```

Output:
```csv
TODO
```
