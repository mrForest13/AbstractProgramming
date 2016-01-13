template<int N> struct Factorial {
	enum { val = N*Factorial<N - 1>::val };
};

template<> struct Factorial<0> {
	enum { val = 1 };
};
