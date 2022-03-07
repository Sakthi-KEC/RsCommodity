//package net.microservices.RsCommodity;
//
//import net.microservices.RsCommodity.controller.RsCommodityController;
//import net.microservices.RsCommodity.dto.RsCommodityDto;
//import net.microservices.RsCommodity.service.RsCommodityService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(SpringRunner.class)
//@WebFluxTest(controllers = RsCommodityController.class)
//@Import(RsCommodityService.class)
//class RsCommodityApplicationTests {
//
//	@Autowired
//	private WebTestClient webTestClient;
//	@MockBean
//	private RsCommodityService service;
//
//	@Test
//	public void addProductTest() {
//		Mono<RsCommodityDto> productDtoMono = Mono.just(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",false));
//		when(service.addCommodity(productDtoMono)).thenReturn(productDtoMono);
//
//		webTestClient.post().uri("/commodity")
//				.body(Mono.just(productDtoMono),RsCommodityDto.class)
//				.exchange()
//				.expectStatus().isOk();//200
//
//	}
//
//	@Test
//	public void getProductsTest(){
//		Flux<RsCommodityDto> productDtoFlux=Flux.just(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",false));
//
//		when(service.getAllCommodity()).thenReturn(productDtoFlux);
//
//		Flux<RsCommodityDto> responseBody = webTestClient.get().uri("/commodity")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(RsCommodityDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNext(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",false))
//				.verifyComplete();
//
//	}
//
//	@Test
//	public void getCodeTest(){
//		Mono<RsCommodityDto> productDtoFlux=Mono.just(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",false));
//
//		when(service.getCommodityById("A01")).thenReturn(productDtoFlux);
//
//		Flux<RsCommodityDto> responseBody = webTestClient.get().uri("/commodity/A01")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(RsCommodityDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNextMatches(p->p.getCommodityCode().equals("A01"))
//				.verifyComplete();
//	}
//
//	@Test
//	public void getNameTest(){
//		Flux<RsCommodityDto> productDtoFlux=Flux.just(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",false));
//
//		when(service.getCommodityByName("Nike")).thenReturn(productDtoFlux);
//
//		Flux<RsCommodityDto> responseBody = webTestClient.get().uri("/commodity/name/Nike")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(RsCommodityDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNextMatches(p->p.getCommodityName().equals("Nike"))
//				.verifyComplete();
//	}
//
//	@Test
//	public void getCommodityGroupCodeTest(){
//		Flux<RsCommodityDto> productDtoFlux=Flux.just(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",false));
//
//		when(service.getCommodityByCommodityGroupCode("Shoe-V")).thenReturn(productDtoFlux);
//
//		Flux<RsCommodityDto> responseBody = webTestClient.get().uri("/commodity/groupcode/Shoe-V")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(RsCommodityDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNextMatches(p->p.getCommodityGroupCode().equals("Shoe-V"))
//				.verifyComplete();
//
//	}
//
//	@Test
//	public void getCreatedByTest(){
//		Flux<RsCommodityDto> productDtoFlux=Flux.just(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",false));
//
//		when(service.getCommodityByCreatedBy("JKR")).thenReturn(productDtoFlux);
//
//		Flux<RsCommodityDto> responseBody = webTestClient.get().uri("/commodity/createdby/JKR")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(RsCommodityDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNextMatches(p->p.getCreatedBy().equals("JKR"))
//				.verifyComplete();
//
//	}
//
//	@Test
//	public void addSaveProductTest() {
//		Mono<RsCommodityDto> productDtoMono = Mono.just(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",true));
//		when(service.addsearchedcommodity(productDtoMono)).thenReturn(productDtoMono);
//
//		webTestClient.put().uri("/commodity/savedsearch")
//				.body(Mono.just(productDtoMono),RsCommodityDto.class)
//				.exchange()
//				.expectStatus().isOk();//200
//
//	}
//
//	@Test
//	public void getSavedProductsTest(){
//		Flux<RsCommodityDto> productDtoFlux=Flux.just(new RsCommodityDto("A01","Nike","Best Brand","Shoe-V",true,"JKR","12-09-2021",false));
//
//		when(service.getSavedCommodityGroupCode("Shoe-V")).thenReturn(productDtoFlux);
//
//		Flux<RsCommodityDto> responseBody = webTestClient.get().uri("/commodity/saved/Shoe-V")
//				.exchange()
//				.expectStatus().isOk()
//				.returnResult(RsCommodityDto.class)
//				.getResponseBody();
//
//		StepVerifier.create(responseBody)
//				.expectSubscription()
//				.expectNextMatches(p->p.getCommodityGroupCode().equals("Shoe-V"))
//				.verifyComplete();
//
//	}
//
//
//}
