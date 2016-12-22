package com.vsouhrada.android.lib.mflow.annotations;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * <p>This is annotation that itself has a {@link Qualifier} annotation.</p>
 * <p>This annotation can be used as an identifier (or scope) of objects which are very close to an activity
 * and could be destroy with activity.
 * </p>
 *
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 * @deprecated not used anymore
 */
@Qualifier
@Retention(RUNTIME)
@Deprecated
public @interface ForActivity {

}
