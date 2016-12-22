package com.vsouhrada.android.lib.mflow.annotations;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * <p>This is annotation that itself has a {@link Qualifier} annotation.</p>
 * <p>This annotation can be used as an identifier (or scope) of objects which are implementations of the {@link android.widget.ArrayAdapter}
 * and could be used as an adapters for the Navigation Drawer.
 * </p>
 *
 * @author vsouhrada
 * @version 1.0.0
 * @since 1.0.0
 */
@Qualifier
@Retention(RUNTIME)
public @interface DrawerAdapter {

}
