/*
 * package zycus;
 * 
 * import java.io.File; import java.io.FileInputStream; import
 * java.io.FileNotFoundException; import java.io.FileOutputStream; import
 * java.io.IOException; import java.io.ObjectInputStream; import
 * java.io.ObjectOutputStream; import java.io.Serializable; import
 * java.util.Random;
 * 
 * public class check<K, V> extends CustomTreeMap<K, V> implements Serializable
 * { private CustomTreeMap<K, V> map1;
 * 
 * public check() { map1 = new CustomTreeMap<K, V>(); }
 * 
 * public static void main(String[] args) {
 * 
 * CustomTreeMap<Integer, Integer> c1 = new CustomTreeMap<Integer, Integer>();
 * CustomTreeMap<Integer, Integer> c2 = new CustomTreeMap<Integer, Integer>();
 * System.out.println("by default size of each map : 10");
 * System.out.println(c1.put(11, 21)); System.out.println(c1.put(10, 23)); //
 * System.out.println(c1.put(8, 22)); System.out.println("c1 map print : " +
 * c1.toString());
 * 
 * System.out.println("c1 first key : " + c1.firstKey()); System.out.println(
 * "c1 key set : " + c1.keySet()); System.out.println("c1 last key : " +
 * c1.lastKey()); System.out.println("c1 values : " + c1.values());
 * 
 * c2.clear(); System.out.println("c2 is empty : " + c2.isEmpty());
 * System.out.println("put some values in c2"); c2.put(29, 21); c2.put(190, 23);
 * c2.put(1011, 21); c2.put(1220, 23); c2.put(121, 21); c2.put(120, 23);
 * c2.put(1211, 21); // c2.put(1320, 23); // c2.put(12121, 21); // c2.put(1520,
 * 23); // c2.put(1261, 21); // c2.put(1270, 23); // c2.put(1271, 23); //
 * c2.put(1221, 23);
 * 
 * c1.putAll(c2);
 * 
 * System.out.println("c1.FloorEntry  : for key 50 " +
 * c1.floorEntry(50).getKey());
 * 
 * System.out.println("c1.headMap(12) : " + c1.headMap(11).toString());
 * 
 * System.out.println("Decending key set: " + c1.descendingKeySet());
 * 
 * System.out.println("headmap(11):" + c1.headMap(11).toString());
 * System.out.println("navigable map from Key in c2 ; " +
 * c2.tailMap(121).toString());
 * 
 * System.out.println("serialization test:"); // Serialization try { File file =
 * new File("D:\\lalit_try.txt"); file.createNewFile();
 * 
 * ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(file));
 * ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file));
 * 
 * try { System.out.println("Write c1 object to file .. "); oo.writeObject(c1);
 * oo.close();
 * 
 * System.out.println("Read c1 from the file.. "); Object o = oi.readObject();
 * System.out.println(o.toString()); oi.close(); } catch (ClassNotFoundException
 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
 * 
 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
 * block e.printStackTrace(); }
 * 
 * Thread t1 = new Thread(new Runnable() {
 * 
 * @Override public void run() { // TODO Auto-generated method stub Random r =
 * new Random(); for (int i = 0; i < 100; i++) { int key = r.nextInt(10000); int
 * value = r.nextInt(46) * 40; c2.put(key, value); System.out.println(
 * "put key into c2: " + key + " and value " + value); try { Thread.sleep(100);
 * } catch (InterruptedException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } } } }, "t1");
 * 
 * Thread t2 = new Thread(new Runnable() {
 * 
 * @Override public void run() { // TODO Auto-generated method stub Random r =
 * new Random(); for (int i = 0; i < 100; i++) { int n = r.nextInt(10); try {
 * Thread.sleep(100); } catch (InterruptedException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } if (n == 1) { c2.remove(r.nextInt(1000));
 * System.out.println("removed object: .. ");
 * 
 * } } } }, "t2");
 * 
 * t1.start(); t2.start(); try { t1.join(); t2.join(); } catch
 * (InterruptedException e) { // TODO Auto-generated catch block
 * e.printStackTrace();
 * 
 * }
 * 
 * } }
 */