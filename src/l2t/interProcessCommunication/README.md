# Socket
> Server
```Java
ServerSocket ss = new ServerSocket(port)
Socket s = ss.accpet()
DataInputStream dis = new DataInputStream(s.getInputStream());
dis.readInt();
dis.readFully(byte[]);
```
>client
```Java
Socket s = newSocket(host, port);
DatOutputStream dos = new DataInputStream(s.getOutputStream());
dos.writeInt(1);
dis.write(byte[]);
```
# Object and Byte
> Obj -> byte[]
>> ByteArrayOutputStream os = new ByteArrayOutputStream();
>> ObjectOutputStream oos = new ObjectOutputStream(os);
>> oos.writeObject(obj);
>> bytes = os.toByteArray();

> byte[] -> Object
>> ByteArrayInputStream is = new ByteArrayInputStream(bytes);
>> ObjectInputStream os = new ObjectInputStream(is);
>> Object obj = os.readObject()