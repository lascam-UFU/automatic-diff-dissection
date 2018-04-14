# Patch Clustering

This project extracts features from patches.

## Usage

### Extract features from the defects4j dataset
1. Clone [defects4j](https://github.com/rjust/defects4j)
2. Checkout each bug following this architecture: `<project_id>/<project_id>_<bugid>/` (ex:`math/math_1/` the project name is in lower case)
```bash
destination="<destination_folder>"
for bug in $(seq 1 26); do defects4j checkout -p Chart -v ${bug}b -w ${destination}/chart/chart_${bug}; done    
for bug in $(seq 1 133); do defects4j checkout -p Closure -v ${bug}b -w ${destination}/closure/closure_${bug}; done
for bug in $(seq 1 65); do defects4j checkout -p Lang -v ${bug}b -w ${destination}/lang/lang_${bug}; done
for bug in $(seq 1 106); do defects4j checkout -p Math -v ${bug}b -w ${destination}/math/math_${bug}; done
for bug in $(seq 1 27); do defects4j checkout -p Time -v ${bug}b -w ${destination}/time/time_${bug}; done
for bug in $(seq 1 38); do defects4j checkout -p Mockito -v ${bug}b -w ${destination}/mockito/mockito_${bug}; done
```
3. Checkout each patch is a separated directory with the same architecture
4. Change the configuration in `config.cfg` file
5. compile the java project `mvn package`
6. run the feature extractor: `python src/main/python/Main.py`

Output:
```csv
Project	Bug ID	# Files	# Variables	# variable accesses	# invocations	# external invocations	# if	# loops	# types	# comments	# assignments	# binary expressions	# unary expressions	# instantiations	# try/catch	# literals	# throws	# returns	# breaks	# continues	# Variables	# variable accesses	# invocations	# external invocations	# if	# loops	# types	# comments	# assignments	# binary expressions	# unary expressions	# instantiations	# try/catch	# literals	# throws	# returns	# breaks	# continues	
Chart	4	1	6	8	3	7	5	5	0	0	0	0	0	1	0	0	0	0	0	0	0	0	3	8	5	5	1	0	0	0	0	2	0	0	0	1	0	0	0	0	
```
    