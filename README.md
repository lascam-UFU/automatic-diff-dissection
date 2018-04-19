# Patch Clustering [![Build Status](https://travis-ci.org/tdurieux/patch-clustering.svg?branch=master)](https://travis-ci.org/tdurieux/patch-clustering) [![Coverage Status](https://coveralls.io/repos/github/tdurieux/patch-clustering/badge.svg?branch=master)](https://coveralls.io/github/tdurieux/patch-clustering?branch=master)

This project extracts features from patches.

## Usage

1. Clone this repository:

```bash
git clone https://github.com/tdurieux/patch-clustering.git
```

2. Run the script `./init.sh`:

```bash
cd patch-clustering
chmod +x ./scripts/*.sh
./scripts/init.sh
```

### Extract features from the Defects4J dataset

1. Clone and init Defects4J anywhere: follow instructions [here](https://github.com/rjust/defects4j)

2. Checkout each bug and patch by running the following script (WARNING: 12,3 GB in disk will be used):

```bash
./patch-clustering/scripts/defects4j/checkout-defects4j/checkout_all.sh
```

3. Change the configuration in `/patch-clustering/scripts/defects4j/config.cfg` file:

- `defects4j` must be set with the path of the Defects4J repository cloned in Step 1.
- `checkout` must be set with the path of the directory containing the buggy versions checked out in Step 2.
- `fix_checkout` must be set with the path of the directory containing the fixed versions checked out in Step 2.

4. Enter in the directory where this project was cloned and compile this project:

```bash
mvn package
```

5. Run the feature extractor:

```bash
python scripts/defects4j/python/Main.py <MODE>
```

Available modes:
`REPAIR_PATTERNS`, `REPAIR_ACTIONS`, `METRICS`, `ALL`

Output:
```csv
TODO
```
