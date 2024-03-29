package willem.weiyu.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author weiyu
 * @Description
 * @Date 2019/4/19 12:44
 */
public class SimpleNettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder());
            }
        });
        Channel channel = bootstrap.connect("127.0.0.1", 8088).channel();
        while (true){
            channel.writeAndFlush(System.currentTimeMillis()+": hello netty");
            Thread.sleep(200);
        }
    }
}
