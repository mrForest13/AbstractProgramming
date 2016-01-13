template<int N> inline void loop(int *data) {
	if (data[0]>data[1]) std::swap(data[0], data[1]);
	loop<N - 1>(++data);
}

template<> inline void loop<0>(int *data) {};

template<int N>  inline void bubble_sort_template(int * data) {
	loop<N - 1>(data);
	bubble_sort_template<N - 1>(data);
}

template<>  inline void bubble_sort_template<2>(int * data) {
	loop<1>(data);
};
