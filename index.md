## Welcome to ETML Page

Etml is a library for parsing HTML to object with one line and a few annotations :)
 
This project is based on Jsoup(https://jsoup.org/).

## Installation
1. Add Dependency to your pom
```xml
<dependency>
	<groupId>com.github.elzhass</groupId>
	<artifactId>etml</artifactId>
	<version>0.1.1</version>
</dependency>
```
2. Enjoy!

## Usage

Example model should look like this:
```java
import com.github.elzhass.etml.marker.EtmlElement;
import com.github.elzhass.etml.marker.EtmlObject;

import java.util.List;

@EtmlObject
public class TestModel{

    @EtmlElement(selector = "head > title")
    private String name;

    @EtmlElement(selector = "body > h1")
    private Long age;

    @EtmlElement(selector = "body > h2")
    private boolean isHuman;

    @EtmlElement(selector = "body > ul")
    private List<TestModelWithoutBase> attributes;

    @EtmlElement(selector = "body > p")
    private List<String> elements;
    
    //Getters and setters below
}
```
We must annotate target model as @EtmlObject(Submodels can be ignored).
Also, it's important to annotate target fields with @EtmlElement and pass selector.

If you have List of SubModels, you can annotate List with selector:
```java
@EtmlElement(selector = "body > ul")
private List<TestModelWithoutBase> attributes;
```
And fields of that SubModel like this:
```java 
import com.github.elzhass.etml.marker.EtmlElement;

import java.util.List;

public class TestModelWithoutBase {
    @EtmlElement(selector = "li")
    private String test;

    @EtmlElement(selector = "h1")
    private int numb;

    @EtmlElement(selector = "ol")
    private List<String> testText;

    public List<String> getTestText() {
        return testText;
    }

    public String getTest() {
        return test;
    }

    public int getNumb() {
        return numb;
    }
}
```
So at the end you will get your data mapped from this selectors: 
```
body > ul [0] > li
body > ul [0] > h1
body > ul [0] > ...
body > ul [1] > li
body > ul [1] > h1
body > ul [1] > ...
```
And etc.

And last but not least, you can use as input HTML String: 

```java
TestModel model = etml.fromHtml(HTML, TestModel.class);
```

As well as Jsoup Document:
```java
org.jsoup.nodes.Document doc = Jsoup.parse(HTML);
TestModel model = etml.fromHtml(doc, TestModel.class);
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
