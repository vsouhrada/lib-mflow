package com.vsouhrada.android.lib.mflow.annotations;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>This is annotation that itself has a {@link Qualifier} annotation.</p>
 * <p>This annotation can be used as an identifier of objects which comes from the Main Thread (UI thread).</p>
 *
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 * @deprecated use annotation MainThread from lib-di-dagger instead
 */
@Retention(RUNTIME)
@Qualifier
@Deprecated
public @interface MainThread {
}
