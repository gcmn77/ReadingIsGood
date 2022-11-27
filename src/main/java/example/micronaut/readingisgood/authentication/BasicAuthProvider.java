//package example.micronaut.readingisgood.authentication;
//
//import example.micronaut.readingisgood.entity.Customer;
//import example.micronaut.readingisgood.repository.CustomerRepository;
//import io.micronaut.http.HttpRequest;
//import io.micronaut.security.authentication.AuthenticationProvider;
//import io.micronaut.security.authentication.AuthenticationRequest;
//import io.micronaut.security.authentication.AuthenticationResponse;
//import jakarta.inject.Singleton;
//import org.reactivestreams.Publisher;
//import org.springframework.beans.factory.annotation.Autowired;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.FluxSink;
//
//import java.util.Optional;
//
//@Singleton
//public class BasicAuthProvider implements AuthenticationProvider {
//    @Autowired
//    CustomerRepository customerRepository;
//
////    @Override
////    public Publisher<AuthenticationResponse> authenticate(HttpRequest httpReq, AuthenticationRequest authReq) {
////        final String email = authReq.getIdentity().toString();
////        final String password = authReq.getSecret().toString();
////
////        Optional<Customer> existedCustomer = customerRepository.findByEmail(email);
////        return Flux.create(emitter -> {
////            if(existedCustomer.get().getId() != null && existedCustomer.get().getPassword().equals(password)) {
////                emitter.next(AuthenticationResponse.success((String) existedCustomer.get().getEmail()));
////                emitter.complete();
////            } else {
////                emitter.error(AuthenticationResponse.exception());
////            }
////        }, FluxSink.OverflowStrategy.ERROR);
////
////    }
//}
