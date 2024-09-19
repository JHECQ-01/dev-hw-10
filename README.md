# Homework Assignment

This is a simple Java web application that demonstrates how to use Servlets and Filters to display the current time in different time zones.

## Features

- **Time Display**: The `/time` endpoint shows the current time in UTC by default or in a user-specified time zone.
- **Time Zone Parameter**: The user can pass a `timezone` parameter in the query string to view the current time in that time zone. Example: `/time?timezone=UTC+2`.
- **Time Zone Validation**: A filter (`TimezoneValidateFilter`) validates the `timezone` parameter. If an invalid time zone is passed, the application returns an error page with the message "Invalid timezone" and an HTTP status of 400.