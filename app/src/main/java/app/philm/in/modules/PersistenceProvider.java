package app.philm.in.modules;


import android.content.Context;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import app.philm.in.state.AsyncDatabaseHelper;
import app.philm.in.state.AsyncDatabaseHelperImpl;
import app.philm.in.state.DatabaseHelper;
import app.philm.in.state.PhilmSQLiteOpenHelper;
import app.philm.in.util.AndroidFileManager;
import app.philm.in.util.BackgroundExecutor;
import app.philm.in.util.FileManager;
import dagger.Module;
import dagger.Provides;

@Module(
        library = true,
        complete = false,
        includes = {
                ContextProvider.class,
                UtilProvider.class
        }
)
public class PersistenceProvider {

    @Provides @Singleton
    public FileManager provideFileManager(File file) {
        return new AndroidFileManager(file);
    }

    @Provides @Singleton
    public DatabaseHelper getDatabaseHelper(Context context) {
        return new PhilmSQLiteOpenHelper(context);
    }

    @Provides @Singleton
    public AsyncDatabaseHelper getAsyncDatabaseHelper(
            @Named("single") BackgroundExecutor executor,
            DatabaseHelper databaseHelper) {
        return new AsyncDatabaseHelperImpl(executor, databaseHelper);
    }

}
