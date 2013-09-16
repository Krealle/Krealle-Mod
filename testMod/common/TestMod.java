package krealle.TestMod.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.SidedProxy;
import krealle.TestMod.common.TestModCommonProxy;
import krealle.TestMod.common.blocks.BlockLimestone;
import krealle.TestMod.common.handlers.TestModClientPacketHandler;
import krealle.TestMod.common.handlers.TestModServerPacketHandler;

@NetworkMod(clientSideRequired=true,serverSideRequired=true, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"TestMod"}, packetHandler = TestModClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"TestMod"}, packetHandler = TestModServerPacketHandler.class)) //For serverside packet handling

//MOD BASICS
@Mod(modid="TestMod",name="Test Mod",version="Version 0.0.0")

public class TestMod {

@Instance("TutorialMod") //The instance, this is very important later on
public static TestMod instance = new TestMod();

@SidedProxy(clientSide = "krealle.TestMod.client.TestModClientProxy", serverSide = "krealle.TestMod.common.TestModCommonProxy") //Tells Forge the location of your proxies
public static TestModCommonProxy proxy;

//BLOCKS
public static Block Limestone;

@PreInit
public void PreInit(FMLPreInitializationEvent e){

//BLOCKS
Limestone = new BlockLimestone(3000).setUnlocalizedName("Limestone"); //3000 is its ID

}

@Init
public void InitTestMod(FMLInitializationEvent event){ //Your main initialization method

//BLOCKS (METHOD)
proxy.registerBlocks(); //Calls the registerBlocks method -- This wasn't here before, so don't skip over this!

//MULTIPLAYER ABILITY
NetworkRegistry.instance().registerGuiHandler(this, proxy); //Registers the class that deals with GUI data

}
}