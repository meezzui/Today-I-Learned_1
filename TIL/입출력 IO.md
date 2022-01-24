### 자바 입출력
---
#### 입출력이란?
+ 컴퓨터 내부 또는 외부의 장치와 프로그램간의 데이터를 주고받는 것

#### 스트림(stream)
+ 자바에서 입출력을 수행하기 위해 두 대상을 연결하고 데이터를 운반하는데 사용되는 연결통로
+ 먼저 보낸 데이터를 먼저 받게 되어 있으며 중간에 건너뜀 없이 연속적으로 데이터를 주고 받는다.

#### InputStream, OutputStream
+ 모든 바이트기반의 스트림의 조상이며 다양한 메소드가 선언되어 있다.
+ 스트림의 종류에 따라서 mark()와 reset()을 사용하여 이미 읽은 데이터를 되돌려서 다시 읽을 수 있다.
+ flush() : 버퍼가 있는 출력스트림의 경우에만 의미가 있다. OutputSteream에 정의된 flush()는 아무런 일도 하지 않는다.
+ 프로그램이 종료될 때, 사용하고 닫지 않은 스트림을 JVM이 자동적으로 닫아 주기는 하지만, 스트림을 사용해서 모든 작업을 마치고 난 후에는 close()를 호출해서 반드시 닫아주어야 한다. 
+ 그러나 ByteArrayInputStream과 같이 메모리를 사용하는 스트림과 System.in, System.out과 같은 표준 입출력 스트림은 닫아 주지 않아도 된다.

#### 문자기반 스트림 - Reader, Writer
+ 바이트기반 스트림의 이름에서 아래와 같이 바꾸면 된다.
```
InputStream --> Reader
OutputStream --> Writer
```

#### FileInputStream과 FileOutputStream
+ 파일에 입출력을 하기 위한 스트림
+ 커맨드라인으로부터 입력받은 파일의 내용을 읽어서 그대로 화면에 출력하는 예제
```java
import javaj.io.*;
class FileViewer {
  public static void main(String args[]) throws IOException {
    FileInputStream fis = new FileInputStream(args[0]);
    int data = 0;

    while((data=fis.read()) != -1) {
      char c = (char)data;
      System.out.print(c);
    }
  }
}
```
#### BufferedInputStream과 BufferedOutputStream
+ 스트림의 입출력 효율을 높이기 위해 버퍼를 사용하는 보조스트림으로 한 바이트씩 입출력하는 것보다는 
+ 버퍼(바이트 배열)를 이용해서 한 번에 여러 바이트를 입출력하는 것이 빠르기 때문에 대부분의 입출력 작업에 사용된다.
+ BufferedInputStream : 입력소스로 부터 버퍼 크기만큼의 데이터를 읽어다 자신의 내부 버퍼에 저장하고 프로그램에서는 버퍼에 저장된 데이터를 읽으며, 다 읽고 그 다음 데이터를 읽기 위해 read메서드가 호출되면, BufferedInputStream은 입력소스로부터 다시 버퍼크기 만큼의 데이터를 읽어다 버퍼에 저장하는 작업을 반복한다.
+ BufferedOutputStream  : 버퍼를 이용해서 출력소스와 작업을 하게 되는데 프로그램에서 write메서드를 이용한 출력이 BufferedOutputStream의 버퍼에 저장된다. 버퍼가 가득 차면, 그 때 버퍼의 모든 내용을 출력소스에 출력한다. 프로그램에서 모든 출력작업을 마친 후 BufferedOutputStream에 `close()`나 `flush()`를 호출해서 마지막에 버퍼에 있는 모든 내용이 출력소스에 출력되도록 해야 한다.

#### SequenceInputStream
+ 여러 개의 입력스트림을 연속적으로 연결해서 하나의 스트림으로부터 데이터를 읽는 것과 같이 처리할 수 있도록 도와준다. 
+ 큰 파일을 여러개의 작은 파일로 나누었다가 하나의 파일로 합치는 것과 같은 작업을 수행할 때 사용하면 좋을 것이다.
+ 메서드/생성자
  + `SequenceInputStream(Enumeration e)` : Enumeration에 저장된 순서대로 입력스트림을 하나의 스트림으로 연결한다.
  + `SequenceInputStream(InputStream s1, InputStream s2)` : 두 개의 입력스트림을 하나로 연결한다.
+ 사용 예제
```java
[사용예1]
Vector files = new Vector();
files.add(new FileInputStream("file.001"));
files.add(new FileInputStream("file.002"));
SequenceInputStream in = new SequenceInputStream(files.elements());

[사용예2]
FileInputStream file1 = new FileInputStream("file.001");
FileInputStream file2 = new FileInputStream("file.002");
SequenceInputStream in = new SequenceInputStream(file1, file2);
```
#### FileReader와 FileWriter
+ File로부터 Text 데이터를 읽고 쓰는데 사용된다.
```java
//Reader 사용법
Reader reader = new FileReader("myfile.txt");

int data = reader.read();
while(data != -1){
    char dataChar = (char) data;
    data = reader.read();
}
```
```java
//Writer 사용법
Writer writer = new FileWriter("file-output.txt");

writer.write("Hello World Writer");
writer.close();
```
#### InputStreamReader와 OutputStreamWriter
+ Byte 기반 스트림을 문자기반 스트림으로 연결시켜주는 역할을 한다.
+ 주로 파일이나 네트워트 연결로부터 Byte로 제공되는 Text를 문자기반(char)로 읽고 쓰기를 할 경우에 많이 쓰인다.
```java
//FileInputStream을 InputStreamReader로 Wrapping.
Reader inputStreamReader = new InputStreamReader(new FileInputStream("input.txt"), "UTF-8");

int data = inputStreamReader.read();
while(data != -1){
    char theChar = (char) data; //char 값의 정수를 리턴한다.
    data = inputStreamReader.read();
}

inputStreamReader.close();
```
#### 주요 io 클래스 관계
<img width="450" height="400" src="http://www.tutorialspoint.com/java/images/file_io.jpg">

#### 표준입출력 - System.in, System.out, System.err
+ System.in : System.in 은 콘솔의 키보드 인풋에 연결된 inputStream이다.
+ System.out : System.out은 PrintStream이다. 콘솔로 데이터를 내보는 역할을 한다.
+ System.err : System.err PrintStream이다. System.out과 비슷하지만 일반적으로 에러메세지를 내보낼때 사용된다.

#### RandomAccessFile
+ 입력과 출력을 하나의 클래스로 파일에 대한 입력/출력을 모두 할 수 있게 설계된 클래스. 가장 큰 장점은 파일의 어느 위치에나 읽기/쓰기가 가능하다는 점이다.
+ RandomAccessFile 생성
  + `RandomAccessFile file = new RandomAccessFile("file.txt", "rw");` (생성자의 두번쨰 'rw'는 RandomAccessFile의 mode 값이다. rw는 Read/Write를 뜻한다.)
+ RandomAccessFile 이동
```java
RandomAccessFile file = new RandomAccessFile("file.txt", "rw");

//단위 : Byte
file.seek(200);

long pointer = file.getFilePointer();

file.close();
```
+ RandomAccessFile 읽기/쓰기
```java
//읽기 사용법
RandomAccessFile file = new RandomAccessFile("file.txt", "rw");

int aByte = file.read();

file.close();

//쓰기 사용법
RandomAccessFile file = new RandomAccessFile("file.txt", "rw");

file.write("Hello World".getBytes());

file.close();
```

#### File
+ File 클래스로 통해 파일과 디렉토리를 다룰 수 있도록 제공한다
+ File클래스는 파일과 파일의 메타데이터의 접근하는 기능만 제공한다.(만약 파일의 내용을 읽기/쓰기 기능을 원한다면 file Stream을 이용해야한다.)
+ File 초기화 `File file = new File("input-file.txt");`
+ 존재여부 확인(File 객체 생성시 실제 파일이 없어도 예외가 나지 않고 정상적으로 생성된다.) `boolean fileExists = file.exists();`
+ 디렉토리 생성(mkdir() / mkdirs())
```java
File file = new File("c:\\users\\javastudy\\newdir");
boolean dirCreated = file.mkdir();
```
+ 파일 길이 `long length = file.length();`
+ Rename `boolean success = file.renameTo(new File("c:\\data\\new-file.txt"));`
+ 파일 삭제 `boolean success = file.delete();`
+ 파일 리스트(list() / listFiles()) 
```java
File file = new File("c:\\data");

String[] fileNames = file.list();
File[] files = file.listFiles();
```



