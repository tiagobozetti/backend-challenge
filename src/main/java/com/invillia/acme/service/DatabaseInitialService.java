package com.invillia.acme.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.City;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.State;
import com.invillia.acme.domain.Store;
import com.invillia.acme.domain.enums.StatusOrder;
import com.invillia.acme.domain.enums.StatusPayment;
import com.invillia.acme.repositories.AddressRepository;
import com.invillia.acme.repositories.CityRepository;
import com.invillia.acme.repositories.OrderItemRepository;
import com.invillia.acme.repositories.OrderRepository;
import com.invillia.acme.repositories.PaymentRepository;
import com.invillia.acme.repositories.StateRepository;
import com.invillia.acme.repositories.StoreRepository;

@Service
public class DatabaseInitialService {

	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private BCryptPasswordEncoder bCryp;
	
	public void instantiateTestDatabase() throws ParseException {
		State stateAL = new State(null, "Alabama", "AL");
		State stateAK = new State(null, "Alaska", "AK");
		State stateAZ = new State(null, "Arizona", "AZ");
		State stateAR = new State(null, "Arkansas", "AR");
		State stateCA = new State(null, "California", "CA");
		
		stateRepository.saveAll(Arrays.asList(stateAL,stateAK,stateAZ,stateAR,stateCA));
		
		City city1 = new City(null, "Birmingham", stateAL);
		City city2 = new City(null, "Montgomery", stateAL);
		City city3 = new City(null, "Anchorage", stateAK);
		City city4 = new City(null, "Los Angeles", stateCA);
		City city5 = new City(null, "San Diego", stateCA);
		City city6 = new City(null, "San Jose", stateCA);
		cityRepository.saveAll(Arrays.asList(city1,city2,city3,city4,city5,city6));
		
		Store store1 = new Store(null, "Store 1", "store1@gmail.com", bCryp.encode("123"));
		Store store2 = new Store(null, "Store 2", "store2@gmail.com", bCryp.encode("123"));
		Store store3 = new Store(null, "Store 3", "store3@gmail.com", bCryp.encode("123"));
		Store store4 = new Store(null, "Store 4", "store4@gmail.com", bCryp.encode("123"));
		Store store5 = new Store(null, "Store 5", "store5@gmail.com", bCryp.encode("123"));
		
		storeRepository.saveAll(Arrays.asList(store1,store2,store3,store4,store5));
		
		Address address1 = new Address(null,"Street 1","1","11111",store1,city1);
		Address address2 = new Address(null,"Street 2","2","22222",store1,city2);
		Address address3 = new Address(null,"Street 3","3","33333",store2,city3);
		Address address4 = new Address(null,"Street 4","4","44444",store3,city4);
		Address address5 = new Address(null,"Street 5","5","55555",store4,city5);
		addressRepository.saveAll(Arrays.asList(address1,address2,address3,address4,address5));
		
		Order order1 = new Order(null, address1, null, StatusOrder.OPEN.getId());
		Payment payment = new Payment(null, order1, new Date(), "12344567987456321", StatusPayment.OPEN.getId());
		order1.setPayment(payment);
		orderRepository.save(order1);		
		paymentRepository.save(payment);
		
		OrderItem ordemItem1 = new OrderItem(null, order1, "Product One", 100.00, 1.00);
		OrderItem ordemItem2 = new OrderItem(null, order1, "Product Two", 200.00, 2.00);
		OrderItem ordemItem3 = new OrderItem(null, order1, "Product Three", 300.00, 3.00);
		
		orderItemRepository.saveAll(Arrays.asList(ordemItem1,ordemItem2,ordemItem3));
	}
}
