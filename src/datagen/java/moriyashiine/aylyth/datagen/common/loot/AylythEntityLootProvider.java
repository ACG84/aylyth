package moriyashiine.aylyth.datagen.common.loot;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import moriyashiine.aylyth.common.data.tag.AylythItemTags;
import moriyashiine.aylyth.common.entity.AylythEntityTypes;
import moriyashiine.aylyth.common.item.AylythItems;
import moriyashiine.aylyth.common.loot.predicates.ScionPredicate;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.GroupEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class AylythEntityLootProvider extends SimpleFabricLootTableProvider {

    private final Map<Identifier, LootTable.Builder> loot = new Object2ObjectOpenHashMap<>();

    public AylythEntityLootProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator, LootContextTypes.ENTITY);
    }

    protected void generateLoot() {
        addDrop(AylythEntityTypes.AYLYTHIAN, this::aylythian);
        addDrop(AylythEntityTypes.ELDER_AYLYTHIAN, this::elderAylythian);
        addDrop(AylythEntityTypes.SCION, this::scion);
        addDrop(AylythEntityTypes.WREATHED_HIND_ENTITY, this::wreathedHind);
        // Placeholder for future drops
        if (false) {
            addDrop(AylythEntityTypes.YMPEMOULD, this::ympeMouldLoot);
            addDrop(AylythEntityTypes.BONEFLY, this::boneflyLoot);
            addDrop(AylythEntityTypes.TULPA, this::tulpaLoot);
        }
    }

    private LootTable.Builder aylythian(EntityType<?> type) {
        return LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(Items.BONE)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
                .pool(LootPool.builder().with(ItemEntry.builder(Items.STICK)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
                .pool(LootPool.builder().with(ItemEntry.builder(AylythItems.YMPE_SAPLING)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
                .pool(LootPool.builder().with(ItemEntry.builder(AylythItems.YMPE_FRUIT)).conditionally(KilledByPlayerLootCondition.builder()).conditionally(RandomChanceWithLootingLootCondition.builder(0.25f, 0.01f)))
                .pool(LootPool.builder().with(ItemEntry.builder(AylythItems.WRONGMEAT)).conditionally(DamageSourcePropertiesLootCondition.builder(DamageSourcePredicate.Builder.create()
                        .sourceEntity(EntityPredicate.Builder.create()
                                .equipment(EntityEquipmentPredicate.Builder.create()
                                        .mainhand(ItemPredicate.Builder.create()
                                                .tag(AylythItemTags.FLESH_HARVESTERS).build()
                                        ).build()
                                )
                        )
                )).conditionally(RandomChanceWithLootingLootCondition.builder(0.15f, 0.0625f)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2))));
    }

    private LootTable.Builder elderAylythian(EntityType<?> type) {
        return LootTable.builder()
                .poo
