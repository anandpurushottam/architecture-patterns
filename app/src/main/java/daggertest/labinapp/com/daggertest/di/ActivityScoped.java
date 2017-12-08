package daggertest.labinapp.com.daggertest.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ADMIN on 06-12-2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)

public @interface ActivityScoped {
}
