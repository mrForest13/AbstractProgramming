template<int A, int B> struct Nwd {
	enum { val = Nwd<B, A%B>::val };
};

template<int A> struct Nwd<A, 0> {
	enum { val = A };
};
