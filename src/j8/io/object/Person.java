package j8.io.object;

import java.io.*;

public class Person implements Serializable,Externalizable {

    private String name;
    private int age;


   /* private void writeObject(ObjectOutputStream oos) throws Exception {
        DataOutputStream dos = new DataOutputStream(oos);
        dos.writeUTF(name + "::" + age);
    }

    private void readObject(ObjectInputStream ios) throws Exception {
        DataInputStream dos = new DataInputStream(ios);
        String[] obj = dos.readUTF().split("::");
        this.name=obj[0];
        this.age=Integer.valueOf(obj[1]);
    }*/




/*    private Object writeReplace() throws ObjectStreamException{
        return new PersonProxy(name+"::"+age);
    }*/

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String pk = name + "::" + age;
        out.write(pk.getBytes());

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        byte[] buffers = new byte[1024];
        int read = in.read(buffers);
        String content = new String(buffers,0,read);
        String[] pk = content.split("::");
        this.name=pk[0];
        this.age=Integer.valueOf(pk[1]);
    }
}
