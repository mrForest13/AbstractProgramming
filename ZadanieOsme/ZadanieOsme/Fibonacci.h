template<int N> struct Fibonacci {
	enum { val = Fibonacci<N - 1>::val + Fibonacci<N - 2>::val };
};

template<> struct Fibonacci<1> {
	enum { val = 1 };
};

template<> struct Fibonacci<2> {
	enum { val = 1 };
};

