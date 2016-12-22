package com.vsouhrada.android.lib.mflow.annotations;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * <p>This is annotation that itself has a {@link Qualifier} annotation.</p>
 * <p>This annotation can be used as an identifier (or scope) of objects which are close to the Android
 * {@link android.app.Application} class.
 * </p>
 *
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 * @deprecated use annotation ForApplication from lib-di-dagger instead
 */
@Qualifier
@Retention(RUNTIME)
@Deprecated
public @interface ForApplication {

}
