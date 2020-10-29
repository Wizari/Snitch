package com.gmail.wizaripost.snitch.repository;

import javax.inject.Singleton;

@Singleton
public class RepositoryFromXml {
//
//    private final IFileProvider fileProvider;
//
//
//    @Inject
//    public RepositoryFromXml(IFileProvider fileProvider) {
//        this.fileProvider = fileProvider;
//        File file = new File(this.fileProvider.getFileName());
//        if (!file.exists()) {
//            this.saveDescriptions(new ArrayList<>());
//        }
//
//    }
//
//    private Collection<Employee> loadFromFile(String fileName) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(DataStorage.class);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            DataStorage result = (DataStorage) unmarshaller.unmarshal(new File(fileName));
//            return result.getEmployeeDescriptors();
//        } catch (JAXBException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void saveToFile(Collection<Employee> wordDescriptions, String fileName) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(DataStorage.class);
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(new DataStorage(new ArrayList<>(wordDescriptions)), new File(fileName));
//        } catch (JAXBException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
////    @Override
//    public List<Employee> getAllWordDescriptions() {
//        return new ArrayList<>(Optional.ofNullable(this.loadFromFile(this.fileProvider.getFileName())).orElse(new ArrayList<>()));
//    }
//
////    @Override
//    public void saveDescriptions(Collection<Employee> wordDescriptions) {
//        this.saveToFile(wordDescriptions, this.fileProvider.getFileName());
//    }
//
//
//
////    @Override
//    public void saveDescription(final Employee wordDescription) {
//        Collection<Employee> wordDescriptions = Optional.ofNullable(this.loadFromFile(this.fileProvider.getFileName())).orElse(new ArrayList<>());
//        wordDescriptions.remove(wordDescription);
//        wordDescriptions.add(wordDescription);
//        this.saveDescriptions(wordDescriptions);
//    }
//
////    @Override
//    public void removeWordDescription(Employee wordDescription) {
//        Collection<Employee> wordDescriptions = this.loadFromFile(this.fileProvider.getFileName());
//        wordDescriptions.remove(wordDescription);
//        this.saveDescriptions(wordDescriptions);
//    }
//
////    @Override
//    public void exportWordDescription(File file) {
//        Collection<Employee> wordDescriptions = Optional.ofNullable(this.loadFromFile(this.fileProvider.getFileName())).orElse(new ArrayList<>());
//        this.saveToFile(wordDescriptions, file.getName());
//    }
}
