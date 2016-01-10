package com.transactionservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transactionservice.model.Transaction;

@RestController
@RequestMapping("/")
public class SpringServiceController {

	//Create list of transactions using constructor (In memory object)
	//Transaction(long id, int parent_id, String type, double amount)
	Transaction trans= new Transaction(1, 10, "cars", 100);
	Transaction trans2= new Transaction(2, 20, "bikes", 200);
	Transaction trans3= new Transaction(3, 20, "bikes", 50);
	Transaction trans4= new Transaction(4, 20, "bikes", 300);;
	Transaction trans5= new Transaction(5, 30, "shopping", 300);

	//In memory object
	List <Transaction> transList= new ArrayList<Transaction>();

	//default Constructor for initiating the 'in memory' object.
	SpringServiceController(){
		//Set transaction list on creation
		setTransactionList();
	}
	
	//Set transaction list(In memory object)
	public void setTransactionList(){
			transList.add(trans);
			transList.add(trans2);
			transList.add(trans3);
			transList.add(trans4);
			transList.add(trans5);
	}
	
	 //Add object to In memory object
	public void addObject(Transaction obj){
		transList.add(obj);
	}
	
	 //Get the In memory object
	public List <Transaction> getAllTransactions(){
		return transList;
	}
	
	public void removeObject(Transaction obj){
		transList.remove(obj);
	}
	
	/*------------------------------------REST METHODS-------------------------------*/

	//Get Transaction object by id
	@RequestMapping(value = "/transaction/{tranaction_id}", method = RequestMethod.GET)
	public Transaction getObject(@PathVariable int tranaction_id) {
		for(Transaction obj: transList){
			//check if type matches with all transaction objects
			if(obj.getId()==tranaction_id){
				return obj;
			}
		}
		return null;
	}
	

	@RequestMapping(value = "/transaction/{tranaction_id}", method = RequestMethod.PUT)
	public ResponseEntity<Transaction> putObject(@RequestBody Transaction transaction, @PathVariable int tranaction_id) {
		System.out.println("amount: "+ transaction.getAmount());
		System.out.println("type: "+ transaction.getType());
		System.out.println("parent id: "+ transaction.getParent_id());
		
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

	/*
	 * Due to @RestController annotation, whatever we return from our method, it is converted in JSON.
	 * Following method creates a list of transaction ids of same type and returns to browser as json object
	 * 
	 * */
	@RequestMapping(value = "/transaction/types/{type}", method = RequestMethod.GET)
	public List<Long> getObject(@PathVariable String type) {
		List<Long> transactionsIdList = new ArrayList<Long>();
		for(Transaction obj: transList){
			//check if type matches with all transaction objects
			if(obj.getType().equals(type)){
				transactionsIdList.add(obj.getId());
			}
		}
		return transactionsIdList;
	}
	
	/*
	 * Following method returns the sum of amount of a passed id in url.
	 * */
	@RequestMapping(value = "/transaction/sum/{transaction_id}", method = RequestMethod.GET)
	public HashMap<String, Double> getSum(@PathVariable int transaction_id) {
		HashMap<String, Double> map= new HashMap<String, Double>();
		Double sum=0.0;
		for(Transaction obj: transList){
			//check if type matches with all transaction objects
			if(obj.getId()== transaction_id){
				sum=sum+obj.getAmount();
			}
		}
		map.put("sum", sum);
		return map;
	}
	
	//Simply show welcome message
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to Transaction Services!!";
	}
}



