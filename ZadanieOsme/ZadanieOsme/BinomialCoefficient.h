template<int N, int K> struct BinomialCoefficient {
	enum { val = BinomialCoefficient<N - 1, K - 1>::val + BinomialCoefficient<N - 1, K>::val };
};

template<int N> struct BinomialCoefficient<N, 0> {
	enum { val = 1 };
};

template<int N> struct BinomialCoefficient<N, N> {
	enum { val = 1 };
};
