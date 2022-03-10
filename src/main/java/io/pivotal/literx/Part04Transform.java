package io.pivotal.literx;

import java.util.Locale;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

//========================================================================================

	// TODO Capitalize the user username, firstname and lastname
	Mono<User> capitalizeOne(Mono<User> mono) {
		return mono.map((User user) -> {
			User caplitalUser = new User(
				user.getUsername().toUpperCase(Locale.ROOT),
				user.getFirstname().toUpperCase(Locale.ROOT),
				user.getLastname().toUpperCase(Locale.ROOT)
			);
			return caplitalUser;
		});
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName
	Flux<User> capitalizeMany(Flux<User> flux) {
		return flux.map((User user) -> {
			User caplitalUser = new User(
				user.getUsername().toUpperCase(Locale.ROOT),
				user.getFirstname().toUpperCase(Locale.ROOT),
				user.getLastname().toUpperCase(Locale.ROOT)
			);
			return caplitalUser;
		});
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
	Flux<User> asyncCapitalizeMany(Flux<User> flux) {
		return null;
	}

	Mono<User> asyncCapitalizeUser(User u) {
		return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
	}

}
