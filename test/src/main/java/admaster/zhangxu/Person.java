package admaster.zhangxu;

import redis.clients.jedis.Jedis;

import java.io.*;

public class Person implements Serializable {


	private int id;
	private String name;

	public void setId (int id) {

		this.id = id;
	}

	public void setName (String name) {

		this.name = name;
	}

	public int getId () {

		return id;
	}

	public String getName () {

		return name;
	}



    public static void main(String[] args)  {
		Jedis jedis = new Jedis("localhost");

		Person client = new Person();
        client.setId(1000);
        client.setName("Tina");

		jedis.set("client:100".getBytes(), SerializeUtil.serialize(client));
		byte[] person = jedis.get(("client:100").getBytes());

		Person oldclient = (Person) SerializeUtil.unserialize(person);

		System.out.println(oldclient.getName());
		System.out.println(oldclient.getId());
    }
}
