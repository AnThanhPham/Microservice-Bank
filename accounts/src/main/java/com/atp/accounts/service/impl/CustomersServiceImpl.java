package com.atp.accounts.service.impl;

import com.atp.accounts.dto.AccountsDto;
import com.atp.accounts.dto.CardsDto;
import com.atp.accounts.dto.CustomerDetailsDto;
import com.atp.accounts.dto.LoansDto;
import com.atp.accounts.entity.Accounts;
import com.atp.accounts.entity.Customer;
import com.atp.accounts.exception.ResourceNotFoundException;
import com.atp.accounts.mapper.AccountsMapper;
import com.atp.accounts.mapper.CustomerMapper;
import com.atp.accounts.repository.AccountsRepository;
import com.atp.accounts.repository.CustomerRepository;
import com.atp.accounts.service.ICustomersService;
import com.atp.accounts.service.client.CardsFeignClient;
import com.atp.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto res = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        res.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber, correlationId);
        res.setCardsDto(cardsDtoResponseEntity.getBody());

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber, correlationId);
        res.setLoansDto(loansDtoResponseEntity.getBody());

        return res;
    }
}
