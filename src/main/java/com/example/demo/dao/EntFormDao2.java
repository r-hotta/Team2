package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Ent;

@Component

public class EntFormDao2 {
	@Autowired
	private final JdbcTemplate db;

	
	public EntFormDao2(JdbcTemplate db) {
		this.db = db;

	}

	public void insertDb(Ent ent) {
		db.update("INSERT INTO batform (name, age, sinntyou, taijuu, ketuatuue, ketuatusita, memo, type) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
				ent.getName(), ent.getAge(), ent.getSinntyou(), ent.getTaijuu(),ent.getKetuatuue(),ent.getKetuatusita(),ent.getMemo(),ent.getType()
				);}

	public List<Ent> searchDb() {
		String sql = "SELECT * FROM batform ";

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		//画面に表示しやすい形のList(resultDB2)を用意
		List<Ent> resultDb2 = new ArrayList<Ent>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			Ent entformdb = new Ent();

			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setName((String) result1.get("name"));
			entformdb.setAge((String) result1.get("age"));
			entformdb.setSinntyou((String) result1.get("sinntyou"));
			entformdb.setTaijuu((String) result1.get("taijuu"));
			entformdb.setKetuatuue((String) result1.get("ketuatuue"));
			entformdb.setKetuatusita((String) result1.get("ketuatusita"));
			entformdb.setMemo((String) result1.get("memo"));
			entformdb.setType((String) result1.get("type"));
			

			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}

		//Controllerに渡す
		return resultDb2;
	}

	
	//削除(DELETE)
	public void deleteDb(Long id) {
		//コンソールに表示
		System.out.println("削除しました");
		//DBからデータを削除
		db.update("delete from batform where id=?", id);
	}

	public void alldeleteDb(Long id) {
		//コンソールに表示
		System.out.println("削除しました");
		//DBからデータを削除
		db.update("delete from batform");
	}

	//更新の実行(UPDATE)
	public void updateDb(Long id, Ent ent) {
		//コンソールに表示
		System.out.println("編集の実行");
		//UPDATEを実行
		db.update("UPDATE batform SET name = ?, age = ?, sinntyou = ?, taijuu = ?, ketuatuue = ?, ketuatusita = ?, memo = ?, type = ? WHERE id = ?", ent.getName(), ent.getAge(), ent.getSinntyou(), ent.getTaijuu(),
				ent.getKetuatuue(), ent.getKetuatusita(), ent.getMemo(), ent.getType(), id);
	}
}
