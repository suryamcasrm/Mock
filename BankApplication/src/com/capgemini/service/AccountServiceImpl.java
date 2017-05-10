package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		int a,b,c;
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	
	@Override
	public int deposit(int accountNumber,int amount) throws InvalidAccountNumberException
	{
		Account account = accountRepository.searchAccount(accountNumber);
		if(account==null)
		{
			throw new InvalidAccountNumberException();
		}
		int totBal;
		if(accountNumber!=account.getAccountNumber())
		{
			throw new InvalidAccountNumberException();
		}
		
		totBal=account.getAmount()+amount;
		System.out.println(totBal);
			return totBal;
			
		
		
		
	}
	

	
	public int withdraw(int accountNumber,int amount) throws InvalidAccountNumberException,InsufficientBalanceException
	{
		Account account = accountRepository.searchAccount(accountNumber);
		if(account==null)
		{
			throw new InvalidAccountNumberException();
		}
		int totBal;
		if(accountNumber!=account.getAccountNumber())
		{
			throw new InvalidAccountNumberException();
		}
		if(amount>account.getAmount())
			throw new InsufficientBalanceException();
		
		totBal=account.getAmount()-amount;
		System.out.println(totBal);
			return totBal;
			
		
		
		
	}

}
