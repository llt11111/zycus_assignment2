package zycus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class CustomTreeMap<K, V> extends Object implements NavigableMap<K, V>, Serializable, Cloneable {
	private static final int MAX_COUNT = 20000;
	private TreeMap<K, V> map;

	public CustomTreeMap() {
		map = new TreeMap<K, V>();
	}

	public CustomTreeMap(Comparator<K> comparator) {
		map = new TreeMap<K, V>(comparator);
	}

	public CustomTreeMap(SortedMap<K, V> sm) {
		map = new TreeMap<K, V>(sm);
	}

	@Override
	public String toString() {
		return map.toString();
	}

	@Override
	public Comparator<? super K> comparator() {
		Comparator<? super K> myComp = new Comparator<K>() {
			@Override
			public int compare(K o1, K o2) {
				if ((o1 instanceof Comparable) && (o2 instanceof Comparable)) {
					if (o1 != null && o2 != null) {
						return o1.hashCode() - o2.hashCode();
					} else
						throw new NullPointerException();
				} else {
					throw new ClassCastException();
				}
			}
		};
		return myComp;
	}


	@Override
	public int hashCode() {
		synchronized (map) {
			int h = 0;
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				h += iter.next().hashCode();
			}
			return h;
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Object clonemap = null;
		synchronized (map) {
			clonemap = new CustomTreeMap<>(map);
		}
		return clonemap;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = map.entrySet();
		if (!set.isEmpty())
			synchronized (map) {
				return set;
			}
		return null;
	}

	@Override
	public K firstKey() {
		if (map.isEmpty()) {
			try {
				throw new NoSuchElementException();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		java.util.List<K> list = new ArrayList<K>(map.keySet());
		return list.get(0);

	}

	@Override
	public Set<K> keySet() {
		if (map.isEmpty()) {
			return null;
		}
		return map.keySet();

	}

	@Override
	public K lastKey() {
		java.util.List<K> list = new ArrayList<K>(map.keySet());
		return list.get(list.size() - 1);

	}

	@Override
	public Collection<V> values() {
		return map.values();
	}

	@Override
	public void clear() {
		Set<K> set = map.keySet();
		for (K s : set) {
			set.remove(s);
		}

	}

	@Override
	public boolean containsKey(Object key) {
		Set<K> set = map.keySet();
		if (set.contains(key))
			return true;
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		Set<Map.Entry<K, V>> set = map.entrySet();
		java.util.List<Map.Entry<K, V>> list = new ArrayList<>(set);
		for (Map.Entry<K, V> m : list) {
			if (m.getValue().equals(value))
				return true;
		}

		return false;
	}

	@Override
	public V get(Object key) {
		if (key == null) {
			throw new NullPointerException("::Key can not be null");
		}
		synchronized (map) {
			return (V) map.get(key);
		}

	}

	@Override
	public boolean isEmpty() {
		synchronized (map) {
			return map.isEmpty();
		}
	}

	@Override
	public V put(K key, V value) {
		if (key.equals(null))
			return null;
		else {
			synchronized (map) {
				if (map.size() > MAX_COUNT) {
					throw new StackOverflowError("Tree map size overflow");
				} else {
					return map.put(key, value);
				}
			}
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		map.putAll(m);
	}

	@Override
	public V remove(Object key) {
		if (key == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		synchronized (map) {
			if (map.containsKey(key)) {
				map.remove(key);
			}
		}

		return null;
	}

	@Override
	public int size() {
		return map.keySet().size();
	}

	@Override
	public java.util.Map.Entry<K, V> ceilingEntry(K key) {
		if (key == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

		Set<Map.Entry<K, V>> set = map.entrySet();
		for (Map.Entry<K, V> e : set) {
			if (key.hashCode() - e.getKey().hashCode() <= 0) {
				return e;
			}
		}
		System.out.println("Entry not found. exiting program ..");
		return null;
	}

	@Override
	public K ceilingKey(K key) {
		Set<Map.Entry<K, V>> set = map.entrySet();
		for (Map.Entry<K, V> e : set) {
			if (key.hashCode() - e.getKey().hashCode() <= 0) {
				return e.getKey();
			}
		}
		return null;
	}

	@Override
	public NavigableSet<K> descendingKeySet() {

		CustomTreeMap<K, V> temp = new CustomTreeMap<K, V>(new Comparator<K>() {
			@Override
			public int compare(K o1, K o2) {
				if ((o1 instanceof Comparable) && (o2 instanceof Comparable)) {
					if (o1 != null && o2 != null) {
						return o2.hashCode() - o1.hashCode();
					} else
						throw new NullPointerException();
				} else {
					throw new ClassCastException();
				}
			}
		});
		// traverse and add to temp map then return temp keyset();
		Set<K> mapset = map.keySet();
		for (K k : mapset) {
			temp.put(k, get(k));
		}
		return (NavigableSet<K>) temp.keySet();
	}

	@Override
	public NavigableMap<K, V> descendingMap() {
		return map.descendingMap();
	}

	@Override
	public java.util.Map.Entry<K, V> firstEntry() {
		if (map.isEmpty()) {
			return null;
		}
		List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
		return list.get(0);
	}

	@Override
	public java.util.Map.Entry<K, V> floorEntry(K key) {
		if (key == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		Set<Map.Entry<K, V>> set = map.entrySet();
		for (Map.Entry<K, V> e : set) {
			if (e.getKey().hashCode() - key.hashCode() <= 0) {
				return e;
			}
		}
		System.out.println("floor entry not found .. exiting program");
		return null;
	}

	@Override
	public K floorKey(K key) {
		if (key == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return (K) map.floorKey(key);
	}

	@Override
	public SortedMap<K, V> headMap(K toKey) {
		if (toKey == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		CustomTreeMap<K, V> temp = new CustomTreeMap<K, V>();
		Set<K> set = map.keySet();
		for (K k : set) {
			if (k.hashCode() - toKey.hashCode() < 0) {
				temp.put(k, get(k));
			}
		}
		return temp;
	}

	@Override
	public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
		return map.headMap(toKey, inclusive);
	}

	@Override
	public java.util.Map.Entry<K, V> higherEntry(K key) {
		Set<Map.Entry<K, V>> set = map.entrySet();
		for (Map.Entry<K, V> e : set) {
			if (e.getKey().hashCode() - key.hashCode() > 0) {
				return e;
			}
		}
		return null;
	}

	@Override
	public K higherKey(K key) {
		if (key == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		Set<Map.Entry<K, V>> set = map.entrySet();
		for (Map.Entry<K, V> e : set) {
			if (e.getKey().hashCode() - key.hashCode() > 0) {
				return e.getKey();
			}
		}
		return null;
	}

	@Override
	public java.util.Map.Entry<K, V> lastEntry() {
		List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
		return list.get(list.size() - 1);
	}

	@Override
	public java.util.Map.Entry<K, V> lowerEntry(K key) {
		if (key == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		Set<Map.Entry<K, V>> set = map.entrySet();
		for (Map.Entry<K, V> e : set) {
			if (e.getKey().hashCode() - key.hashCode() < 0) {
				return e;
			}
		}
		return null;
	}

	@Override
	public K lowerKey(K key) {

		if (key == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		Set<Map.Entry<K, V>> set = map.entrySet();
		for (Map.Entry<K, V> e : set) {
			if (e.getKey().hashCode() - key.hashCode() < 0) {
				return e.getKey();
			}
		}
		return null;
	}

	@Override
	public NavigableSet<K> navigableKeySet() {
		return map.navigableKeySet();
	}

	@Override
	public java.util.Map.Entry<K, V> pollFirstEntry() {
		return map.pollFirstEntry();

	}

	@Override
	public java.util.Map.Entry<K, V> pollLastEntry() {
		return map.pollLastEntry();
	}

	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey) {
		if (fromKey == null || toKey == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return map.subMap(fromKey, toKey);

	}

	@Override
	public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
		return map.subMap(fromKey, fromInclusive, toKey, toInclusive);
	}

	@Override
	public SortedMap<K, V> tailMap(K fromKey) {
		if (fromKey == null) {
			try {
				throw new NullPointerException("Key can not be null");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		CustomTreeMap<K, V> temp = new CustomTreeMap<K, V>();

		// traverse and add to temp map then return temp keyset();
		Set<K> tempSet = map.keySet();
		for (K k : tempSet) {
			{
				if (k.hashCode() - fromKey.hashCode() >= 0) {
					temp.put(k, get(k));
				}
			}
		}
		return temp;
	}

	@Override
	public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
		return map.tailMap(fromKey, inclusive);
	}

}
