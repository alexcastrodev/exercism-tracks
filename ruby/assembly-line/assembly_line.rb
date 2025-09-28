class AssemblyLine
  MIN_NUMBER_OF_CARS = 221
  
  def initialize(speed)
    @speed = speed
  end

  def production_rate_per_hour
    case @speed
    when 5..8
      (MIN_NUMBER_OF_CARS * @speed) * 0.9
    when 9
      (MIN_NUMBER_OF_CARS * @speed) * 0.8
    when 10
      (MIN_NUMBER_OF_CARS * @speed) * 0.77
    else
      MIN_NUMBER_OF_CARS * @speed
    end
  end

  def working_items_per_minute
    if @speed > 0
      (production_rate_per_hour / 60).floor
    else
      0
    end
  end
end
