# Lexer
Lexical analyzer available in Java.

## Getting Started
### Install
Download the Jar file and add it to your IDE, etc.

### Usage
```java
String exampleLexerCode = "function('test function');";
Lexer lexer = new Lexer(exampleLexerCode);
Tokens tokens = lexer.parse();
```
Basically, these can be parsed.<br>
Because the resulting parsed Tokens class extends ArrayList,<br>
Then you can assign codes as you like.<br>
