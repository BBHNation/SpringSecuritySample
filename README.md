# SpringSecuritySample
It's a Spring Security Sample to show authorization progress.

# Source Code
- Controller
	- LoginController
	- OtherController
	- RegisterController
- Entities
	- User
- Repositories
	- UserRepository
- Requests
	- LoginRequest
	- RegisterRequest
- **Security**
	- CustomAuthenticationProvider
	- GrantedAuthorityImp
	- JWTAuthenticationFilter
	- JWTLoginFilter
	- TokenAuthenticationService
	- WebSercutiryConfig
- Services
	- RegisterService

## Progress
1. **Register progress:**

Register with user name and password. Then password encoder encode raw password and create a user.

2. **Login progress:**

Login with user name and password. `JWTLoginFilter` will catch login request and authenticate the user. If authenticate successfuly, `TokenAuthenticationService` will add token in the response.

3. **Get resources with token:**

If request with token in headers. `JWTAuthenticationFilter` will get authentication info from token, then save in context.
