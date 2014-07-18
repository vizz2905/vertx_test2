Simple test: 100 symbols requested with the MarketMapClientBusOnly, 500 updates for each symbols sent by the MarketLinkDataTest.

What was noticed with this test is that 100 or 500 updates per second makes no difference... the MarketMapClientBusOnly seems to consume only 10k messages per 1200ms on average.

Running the test with 50 updates per second, we get 10k messages in about 2000ms which means that the message updates seems to get saturated along with higher rates of updates.