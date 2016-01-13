#include "stdafx.h"
#include "Fibonacci.h"
#include "Factorial.h"
#include "NumberPi.h"
#include "BinomialCoefficient.h"
#include "Nwd.h"
#include "BubbleSort.h"
#include <algorithm>

using namespace System;
using namespace System::Text;
using namespace System::Collections::Generic;
using namespace Microsoft::VisualStudio::TestTools::UnitTesting;

namespace ZadanieOsme
{
	[TestClass]
	public ref class UnitTest
	{
	private:
		TestContext^ testContextInstance;

	public: 
		/// <summary>
		///Gets or sets the test context which provides
		///information about and functionality for the current test run.
		///</summary>
		property Microsoft::VisualStudio::TestTools::UnitTesting::TestContext^ TestContext
		{
			Microsoft::VisualStudio::TestTools::UnitTesting::TestContext^ get()
			{
				return testContextInstance;
			}
			System::Void set(Microsoft::VisualStudio::TestTools::UnitTesting::TestContext^ value)
			{
				testContextInstance = value;
			}
		};


		[TestMethod]
		void TestBinomialCoefficient()
		{
			Assert::AreEqual(84, (int)BinomialCoefficient<9, 3>::val);
			Assert::AreEqual(210, (int)BinomialCoefficient<10, 4>::val);
			Assert::AreEqual(1, (int)BinomialCoefficient<4, 0>::val);
			Assert::AreEqual(1, (int)BinomialCoefficient<2, 2>::val);
		};

		[TestMethod]
		void TestFactorial()
		{
			Assert::AreEqual(1, (int)Factorial<0>::val);
			Assert::AreEqual(362880, (int)Factorial<9>::val);
			Assert::AreEqual(24, (int)Factorial<4>::val);
			Assert::AreEqual(479001600, (int)Factorial<12>::val);
		};

		[TestMethod]
		void TestFibonacci()
		{
			Assert::AreEqual(1, (int)Fibonacci<1>::val);
			Assert::AreEqual(1, (int)Fibonacci<2>::val);
			Assert::AreEqual(55, (int)Fibonacci<10>::val);
			Assert::AreEqual(75025, (int)Fibonacci<25>::val);
		};

		[TestMethod]
		void TestNumberPi()
		{	

		};

		[TestMethod]
		void TestNwd()
		{
			Assert::AreEqual(12,(int)Nwd<36,48>::val);
			Assert::AreEqual(105, (int)Nwd<2310, 525>::val);
			Assert::AreEqual(100, (int)Nwd<100, 100>::val);
			Assert::AreEqual(36, (int)Nwd<31752, 36>::val);
		};

		[TestMethod]
		void TestBubbleSort()
		{
			int tab[] = { 5, 1, 2, 4, 5 };
			int tabSorted[5];
			std::copy(std::begin(tab), std::end(tab), std::begin(tabSorted));
			std::sort(std::begin(tabSorted),std::end(tabSorted));
			bubble_sort_template<5>(tab);

			for (int i = 0; i <5; i++) {
				Assert::AreEqual(tab[i],tabSorted[i]);
			}
		};
	};
}
