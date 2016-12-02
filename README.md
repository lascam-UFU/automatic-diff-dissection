# Patch Clustering

This project extracts features from patches.

## Usage

### Extract features from the defects4j dataset
1. Clone [defects4j](https://github.com/rjust/defects4j)
2. Checkout each bug following this architecture: `<project_id>/<project_id>-<bugid>/` (ex:`math/math-1/` the project name is in lower case)
3. Checkout each patch is a separated directory with the same architecture
4. Change the configuration in `config.cfg` file
5. compile the java project `mvn package`
6. run the feature extractor: `python src/main/python/Main.py`

Output:
```csv
Project	Bug ID	# Files	# Variables	# variable accesses	# invocations	# external invocations	# if	# loops	# types	# comments	# assignments	# binary expressions	# unary expressions	# instantiations	# try/catch	# literals	# throws	# returns	# breaks	# continues	# Variables	# variable accesses	# invocations	# external invocations	# if	# loops	# types	# comments	# assignments	# binary expressions	# unary expressions	# instantiations	# try/catch	# literals	# throws	# returns	# breaks	# continues	
Chart	4	1	6	8	3	7	5	5	0	0	0	0	0	1	0	0	0	0	0	0	0	0	3	8	5	5	1	0	0	0	0	2	0	0	0	1	0	0	0	0	
```
    