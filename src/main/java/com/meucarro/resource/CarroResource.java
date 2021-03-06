package com.meucarro.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meucarro.model.Carro;
import com.meucarro.model.SimpleCar;

@RestController
public class CarroResource {

	private Map<Integer, Carro> carros;

	public CarroResource() {
		fillCars();
	}
	
	protected void deleteAllCars(){
		carros.clear();
	}
	
	protected void fillCars(){
		carros = new HashMap<Integer, Carro>();

		Carro c1 = new Carro(1, "ZZZ-9876", "VW Gol 1.6", 77238, true, -19.957482, -44.153719);
		Carro c2 = new Carro(2, "AAA-1234", "Fiat Mobi 1.4", 10345, true, -19.954094, -44.156809);
		Carro c3 = new Carro(3, "BBB-5678", "Ford Belina 1.8", 600234, false, -19.954195, -44.150436);

		carros.put(c1.getId(), c1);
		carros.put(c2.getId(), c2);
		carros.put(c3.getId(), c3);
	}

	@RequestMapping(value = "/carros", method = RequestMethod.GET)
	public ResponseEntity<List<Carro>> listar() {
		return new ResponseEntity<List<Carro>>(new ArrayList<Carro>(carros.values()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/carros/renew", method = RequestMethod.GET)
	public ResponseEntity<List<Carro>> renew() {
		deleteAllCars();
		fillCars();
		return new ResponseEntity<List<Carro>>(new ArrayList<Carro>(carros.values()), HttpStatus.OK);
	}

	@RequestMapping(value = "/carros", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody(required = false) SimpleCar simpleCar) {
		Carro carro;
		try {
			Date dt = new Date();

			carro = new Carro(carros.size() + 1, simpleCar.getPlaca(), simpleCar.getDesc(),
					(dt.getSeconds() * 1000), false, simpleCar.getLat(), simpleCar.getLng());

			carros.put(carro.getId(), carro);
			return new ResponseEntity<Carro>(carro, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/carros/{id}", method = RequestMethod.GET)
	public ResponseEntity<Carro> buscar(@PathVariable("id") Integer id) {
		Carro carro = carros.get(id);

		if (carro == null) {
			return new ResponseEntity<Carro>(carro, HttpStatus.NOT_FOUND);
		}

		carro.setUltimoStatus();

		return new ResponseEntity<Carro>(carro, HttpStatus.OK);
	}

	@RequestMapping(value = "/carros/{id}/set/alarme/{novoStatus}", method = RequestMethod.GET)
	public ResponseEntity<Carro> mudarAlarme(@PathVariable("id") Integer id,
			@PathVariable("novoStatus") boolean novoStatus) {
		Carro carro = carros.get(id);

		if (carro == null) {
			return new ResponseEntity<Carro>(carro, HttpStatus.NOT_FOUND);
		}

		carro.setAlarme(novoStatus);
		carro.setUltimoStatus();

		return new ResponseEntity<Carro>(carro, HttpStatus.OK);
	}

	@RequestMapping(value = "/carros/{id}/set/trocaoleo", method = RequestMethod.GET)
	public ResponseEntity<Carro> trocaDeOleo(@PathVariable("id") Integer id) {
		Carro carro = carros.get(id);

		if (carro == null) {
			return new ResponseEntity<Carro>(carro, HttpStatus.NOT_FOUND);
		}

		carro.setUltima_troca_oleo(carro.getHodometro());
		carro.setUltimoStatus();

		return new ResponseEntity<Carro>(carro, HttpStatus.OK);
	}

}
