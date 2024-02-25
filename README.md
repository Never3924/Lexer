# Lexer
Lexical analyzer available in Java.

## Getting Started
### Install
Jarファイルをダウンロードし、お使いのIDEなどに追加してください。

### Usage
```java
String exampleLexerCode = "function('test function');";
Lexer lexer = new Lexer(exampleLexerCode);
Tokens tokens = lexer.parse();
```
基本的にはこれらでパースできます。<br>
パースした結果のTokensクラスはArrayListを拡張していますので、<br>
あとは好きにコードを割り当てたりしてください。<br>
