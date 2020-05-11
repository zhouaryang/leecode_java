package test.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringBufferInputStream;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		序列化对象
//		Serializable();
//		反序列化
//		DesSerializable();
//		测试CPU占满
/*		try {
			testCPULoop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		StringTest();
	}
	
	public static void Serializable(){
		Employee e = new Employee();
		e.name = "Reyan Ali";
		e.address = "Phokka kuan,Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;
		
		try{
			FileOutputStream fileOut = 
					new FileOutputStream("D:/sn.txt");
			ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
			outputStream.writeObject(e);
			outputStream.close();
			fileOut.close();
			System.out.println("Serialized data is saved int D:/sn.txt");
			
		}catch(IOException i){
			i.printStackTrace();
		}
	}
	
	public static void DesSerializable(){
		Employee e = null;
		try{
			FileInputStream fileIn = new FileInputStream("D:/sn.txt"); //throw f
			ObjectInputStream in = new ObjectInputStream(fileIn);      //throw i
			e = (Employee) in.readObject();  //e2
			in.close();
			fileIn.close();
		}catch(FileNotFoundException f){
			f.printStackTrace();
		}catch (IOException i) {
			i.printStackTrace();
		}catch (ClassNotFoundException e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		System.out.println("Deserializad Employee...");
		System.out.println("Name: "+ e.name);
		System.out.println("Address: "+ e.address); 
		System.out.println("SSN: " + e.SSN);
	    System.out.println("Number: " + e.number);
	}
	
//	@GetMapping("/cpu/loop")
    public static void testCPULoop() throws InterruptedException {
        System.out.println("请求cpu死循环");
        Thread.currentThread().setName("loop-thread-cpu");
        int num = 0;
        while (true) {
            num++;
            if (num == Integer.MAX_VALUE) {
                System.out.println("reset");
            }
            num = 0;
        }

    }
    
    public String leak() {
        System.out.println("模拟内存泄漏");
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();
        localVariable.set(new Byte[4096 * 1024]);// 为线程添加变量
        return "ok";
    }
    
    public static void StringTest(){
    	String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = s1+s2;
        String s5 = "helloworld";
        String s6 = new String("helloworld");
        System.out.println(s3==s4);
        System.out.println(s3.hashCode());
        System.out.println(s4.hashCode());
        System.out.println(System.identityHashCode(s3));
        System.out.println(System.identityHashCode(s4));
        System.out.println(System.identityHashCode(s5));
        System.out.println(System.identityHashCode(s6));
    }
}
