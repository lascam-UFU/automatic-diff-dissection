# ADD; Automatic Diff Dissection [![Build Status](https://travis-ci.org/lascam-UFU/automatic-diff-dissection.svg?branch=master)](https://travis-ci.org/lascam-UFU/automatic-diff-dissection) [![Coverage Status](https://coveralls.io/repos/github/lascam-UFU/automatic-diff-dissection/badge.svg?branch=master)](https://coveralls.io/github/lascam-UFU/automatic-diff-dissection?branch=master)

This project extracts features from patches such as repair patterns.

## Usage

### Setup ADD

1. Clone this repository:

```bash
$ git clone https://github.com/lascam-UFU/automatic-diff-dissection.git
```

2. Run the script `./init.sh`:

```bash
$ cd automatic-diff-dissection
$ chmod +x ./scripts/*.sh
$ ./scripts/init.sh
```

### Running ADD

To run ADD, you need to compile it, to locate the .jar file, and to call it:

```bash
$ cd automatic-diff-dissection
$ mvn package -DskipTests
$ ls target/*jar
target/automatic-diff-dissection-0.1-SNAPSHOT.jar
target/automatic-diff-dissection-0.1-SNAPSHOT-jar-with-dependencies.jar
target/automatic-diff-dissection-0.1-SNAPSHOT-sources.jar
$ java -jar target/automatic-diff-dissection-0.1-SNAPSHOT-jar-with-dependencies.jar <arguments>
```

Note that ADD receives a set of arguments to run. The argument options are:

```bash
  (-m|--launcherMode) <REPAIR_PATTERNS;REPAIR_ACTIONS;METRICS;ALL>
        Provide the launcher mode, which is the type of the features that will
        be extracted.

  (-b|--bugId) <bugId>
        Provide the bug id (this is used only for information presentation).

  --buggySourceDirectory <buggySourceDirectory>
        Provide the path to the buggy source code directory of the bug.

  --diff <diffPath>
        Provide the path to the diff file.

  [(-o|--output) <outputDirectory>]
        Provide an existing path to output the extracted features as a JSON file
        (optional).
```

The results will be printed in the terminal, and if a path was provided to the argument `outputDirectory`, the results will be saved in a JSON file.  

TODO: to show an usage case with one bug.

### Example on a dataset: extract features from Defects4J patches

1. Clone `tdurieux/pattern-detector-experiment` anywhere:

```bash
$ git clone https://github.com/tdurieux/pattern-detector-experiment.git
```

2. Change the configuration in `/automatic-diff-dissection/scripts/defects4j/run-tool-on-defects4j/config.cfg` file:

- `checkout` must be set with the path of the repository cloned in step 1 plus `/benchmark/defects4j`.

- `output` should be set with an existing path to output the extracted features as a JSON file (optional).

3. Enter in the directory where this project was cloned and compile this project:

```bash
$ mvn package -DskipTests
```

4. Run the feature extractor:

```bash
$ python scripts/defects4j/run-tool-on-defects4j/Launch.py <REPAIR_PATTERNS;REPAIR_ACTIONS;METRICS;ALL>
```

5. Check out the folder you set `output` to in step 2.
