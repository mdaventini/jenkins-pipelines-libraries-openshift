listView('Projects') {
    // Adds columns to the views.
    columns {
        // Adds a column showing the status of the last build. <hudson.views.StatusColumn/>
        status()
        // Adds a weather report showing the aggregated status of recent builds. <hudson.views.WeatherColumn/>
        weather()
        //<hudson.views.JobColumn/>
        // Adds a column showing the item name.
        name()
        // Adds a column showing the last successful build. <hudson.views.LastSuccessColumn/>
        lastSuccess()
        // Adds a column showing the last failed build. <hudson.views.LastFailureColumn/>
        lastFailure()
        // Adds a column showing the duration of the last build. <hudson.views.LastDurationColumn/>
        lastDuration()
        // Adds a column showing a button for scheduling a build. <hudson.views.BuildButtonColumn/>
        buildButton()
        // Adds a column showing fav icon. requires <hudson.plugins.favorite.column.FavoriteColumn plugin="favorite@2.3.2"/>
        favoriteColumn()
    }
    // Sets a description for the view.
    description('Lista de carpetas de proyectos en esta instancia')
    // Adds jobs to the view.
    jobs {
        // Adds jobs to the view.
        name('DevOpsCreateProject')
        // If configured, the regular expression will be applied to all job names.
        regex('(^[A-Z_-]+$)')
    }
}
