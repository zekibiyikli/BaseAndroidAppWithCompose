package com.base.androidappwithcompose.ui.navigation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NavRoutesTest {

    @Test
    fun `Home route is correct`() {
        assertEquals("home", NavRoutes.Home.route)
    }

    @Test
    fun `Dashboard route is correct`() {
        assertEquals("dashboard", NavRoutes.Dashboard.route)
    }

    @Test
    fun `Notifications route is correct`() {
        assertEquals("notifications", NavRoutes.Notifications.route)
    }

    @Test
    fun `NoInternet route is correct`() {
        assertEquals("no_internet", NavRoutes.NoInternet.route)
    }

    @Test
    fun `UserDetail route contains argument placeholder`() {
        assertTrue(NavRoutes.UserDetail.route.contains("{userId}"))
    }

    @Test
    fun `UserDetail ARG_USER_ID is correct`() {
        assertEquals("userId", NavRoutes.UserDetail.ARG_USER_ID)
    }

    @Test
    fun `UserDetail createRoute generates correct path`() {
        val userId = "abc-123"
        assertEquals("user_detail/abc-123", NavRoutes.UserDetail.createRoute(userId))
    }

    @Test
    fun `UserDetail createRoute handles special characters in userId`() {
        val userId = "user@domain.com"
        assertEquals("user_detail/user@domain.com", NavRoutes.UserDetail.createRoute(userId))
    }

    @Test
    fun `All NavRoutes are distinct`() {
        val routes = listOf(
            NavRoutes.Home.route,
            NavRoutes.Dashboard.route,
            NavRoutes.Notifications.route,
            NavRoutes.NoInternet.route,
            NavRoutes.UserDetail.route
        )
        assertEquals(routes.size, routes.toSet().size)
    }
}
