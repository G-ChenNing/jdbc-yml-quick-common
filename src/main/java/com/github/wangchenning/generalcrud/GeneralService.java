package com.github.wangchenning.generalcrud;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.github.wangchenning.leaf.util.json.JsonObj;
import com.github.wangchenning.leaf.util.sql.QuerySystem;
import com.github.wangchenning.leaf.util.sql.SQLGroupResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;



@Service
public class GeneralService {
@Autowired
EntityManagerFactory emf;
@Autowired
DataSource dataSource;
private static QuerySystem querySystem;
static {
	try {
		querySystem = QuerySystem.QuerySystemBuilder.createQuerySystem();
		System.out.println("general service start successfully");
	} catch (IOException e1) {
		System.err.println("general service start fail");
		e1.printStackTrace();
		querySystem = null;
	}
}
/**
 * @param id
 * @param args
 * @return
 * @throws SQLException
 * @throws JsonProcessingException
 */
public String select(String id, JsonObj args) throws SQLException, JsonProcessingException {
	EntityManager em = emf.createEntityManager();
	return querySystem.getQuery(id).select(em, args);
//	return querySystem.getQuery(id).select(dataSource, args).getJSON();
	//	Map<String, Stream<Stream<Entry<String, Object>>>> selectRes = querySystem.getQuery(id).select(dataSource, args);
//	JsonObj jsonObj = JsonObj.instance();
//	selectRes.forEach((k, v) -> {
//		jsonObj.putProperty(k, v.map(s -> {
//			JsonObj temp = JsonObj.instance();
//			s.forEach(e -> {
//				temp.putProperty(e.getKey(), e.getValue());
//			});
//			return temp.toJson();
//		}).collect(Collectors.toList()));
//	});
//	return jsonObj.toJson();
}
public String selectJson(String id, JsonObj args) throws SQLException {
	return querySystem.getQuery(id).select(dataSource, args).getJSON();
}
public String create (String id, JsonObj args) throws SQLException {
	return querySystem.getQuery(id).create(dataSource, args);
}
public void update(String id, JsonObj args) throws SQLException {
	querySystem.getQuery(id).update(dataSource, args);
}
public void delete(String id, JsonObj args) throws SQLException {
	querySystem.getQuery(id).delete(dataSource, args);
}

/**
 * 相比selectJson，该方法更适合在内部适用
 * @param id
 * @param args
 * @return
 * @throws SQLException
 */
@Deprecated
public SQLGroupResult selectResult(String id, JsonObj args) throws SQLException {
	return querySystem.getQuery(id).select(dataSource, args);
}

public void batchInsert(String id, JsonObj args) throws SQLException {
	querySystem.getQuery(id).batchInsert(dataSource, args);
}
public void batchUpdate(String id, JsonObj args) throws SQLException {
	querySystem.getQuery(id).batchUpdate(dataSource, args);
}
}
