# Xilodyne Utilities

Various support libraries.  Check respository src files for possible tests and examples.

Libraries may require other xilodyne-util jars and/or public
projects which can be accessed from maven (add the dependency
to your pom.xml)


## Array Util
Routines for printing arrays.


## Data Util 
NDArray helper util (vectorz implementation)

Maven Requirements:

```
<dependency>
    <groupId>net.mikera</groupId>
    <artifactId>vectorz</artifactId>
    <version>0.62.0</version>
</dependency>
```


## File IO Util
File splitter and line counting routines.

Requirements:

```
xilodyne-util-array-bin.jar
xilodyne-util-logger-bin.jar
xilodyne-util-math-bin.jar
```

Maven requirements:

```
<dependency>
    <groupId>de.siegmar</groupId>
    <artifactId>fastcsv</artifactId>
    <version>1.0.1</version>
</dependency
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```


## jNumpy Util
A few numpy like routines in Java:
* ARange
* L2NumPY
* Linspace
* Meshgrid

Maven Requirements:

```
<dependency>
    <groupId>net.mikera</groupId>
    <artifactId>vectorz</artifactId>
    <version>0.62.0</version>
</dependency>
```


## jPyplot Util
A very simple scatter plot in pyplot method signature.

Requirements:

```
xilodyne-util-logger-bin.jar
```

Maven Requirements:

```
<dependency>
    <groupId>org.jfree</groupId>
    <artifactId>jfreechart</artifactId>
    <version>1.5.0</version>
</dependency>
<dependency>
    <groupId>org.jfree</groupId>
    <artifactId>jcommon</artifactId>
    <version>1.0.23</version>
</dependency>
```


## Logger
Custom logging routines.


## Math
Random range and standard deviation.

Requirements:

```
xilodyne-util-array-bin.jar
xilodyne-util-logger-bin.jar
```


## Metrics
Helper classes to assemble machine learning metrics.

Requirements:

```
xilodyne-util-logger-bin.jar
```


## Pickel Loader Util
jython implementation of pickel loader.

Requirements:

```
xilodyne-util-array-bin.jar
xilodyne-util-fileio-bin.jar
```

Maven Requirements:

```
<dependency>
    <groupId>org.python</groupId>
    <artifactId>jython</artifactId>
    <version>2.7.0</version>
</dependency>
```


## Weka Helper
Classes to convert ARFF to Pickel.

Requirements:

```
xilodyne-util-array-bin.jar
xilodyne-util-logger-bin.jar
xilodyne-util-pickelloader-bin.jar
```

Maven Requirements:

```
<dependency>
    <groupId>net.mikera</groupId>
    <artifactId>vectorz</artifactId>
    <version>0.62.0</version>
</dependency>
<dependency>
    <groupId>nz.ac.waikato.cms.weka</groupId>
    <artifactId>weka-stable</artifactId>
    <version>3.8.0</version>
</dependency>
```


# License

Xilodyne utils are licensed under the MIT License ([link](https://opensource.org/licenses/MIT)).  Other component and libraries licenses are found in the doc directory.


# Authors

**Austin Davis Holiday** - *Initial work* 

I can be reached at [aholiday@xilodyne.com](mailto:aholiday@xilodyne.com)
