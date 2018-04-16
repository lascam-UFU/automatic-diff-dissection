# Patch Clustering [![Build Status](https://travis-ci.org/tdurieux/patch-clustering.svg?branch=master)](https://travis-ci.org/tdurieux/patch-clustering)

This project extracts features from patches.

## Usage

1. Clone this repository:

```bash
git clone https://github.com/tdurieux/patch-clustering.git
```

2. Give permission to run the scripts:

```bash
chmod +x ./patch-clustering/scripts/*.sh
```

3. Clone the repository [SpoonLabs/gumtree-spoon-ast-diff](https://github.com/SpoonLabs/gumtree-spoon-ast-diff) and install it:

```bash
git clone https://github.com/SpoonLabs/gumtree-spoon-ast-diff.git
cd gumtree-spoon-ast-diff/
mvn install
```

### Extract features from the Defects4J dataset

1. Clone and init Defects4J anywhere: follow instructions [here](https://github.com/rjust/defects4j)

2. Checkout each bug and patch by running the following script (WARNING: 12,3 GB in disk will be used):

```bash
./patch-clustering/scripts/checkout-defects4j/checkout_all.sh
```

3. Change the configuration in `config.cfg` file:

- `defects4j` must be set with the path of the Defects4J repository cloned in Step 1.
- `checkout` must be set with the path of the directory containing the buggy versions checked out in Step 2.
- `fix_checkout` must be set with the path of the directory containing the fixed versions checked out in Step 2.

4. Enter in the directory where this project was cloned.

5. Compile this project:

```bash
mvn package
```

6. Run the feature extractor:

```bash
python src/main/python/Main.py <MODE>
```
Available modes: TODO

Output:
```csv
Project	Bug ID	# Files	# Variables	# variable accesses	# invocations	# external invocations	# if	# loops	# types	# comments	# assignments	# binary expressions	# unary expressions	# instantiations	# try/catch	# literals	# throws	# returns	# breaks	# continues	# Variables	# variable accesses	# invocations	# external invocations	# if	# loops	# types	# comments	# assignments	# binary expressions	# unary expressions	# instantiations	# try/catch	# literals	# throws	# returns	# breaks	# continues	
Chart	4	1	6	8	3	7	5	5	0	0	0	0	0	1	0	0	0	0	0	0	0	0	3	8	5	5	1	0	0	0	0	2	0	0	0	1	0	0	0	0	
```
